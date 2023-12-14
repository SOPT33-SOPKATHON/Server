package com.sopt.sopkathonServer.common.exception.slack;

import com.slack.api.Slack;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.webhook.WebhookPayloads;
import com.sopt.sopkathonServer.celeb.domain.Celeb;
import com.sopt.sopkathonServer.room.domain.Room;
import com.sopt.sopkathonServer.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackUtil {

    //application.yml 에 등록해놓은 webhookUrl
    @Value("${slack.webhook.error}")
    private String errorUrl;

    @Value("${slack.webhook.user}")
    private String userUrl;

    private final static String NEW_LINE = "\n";
    private final static String DOUBLE_NEW_LINE = "\n\n";

    private StringBuilder sb = new StringBuilder();

    // Slack으로 알림 보내기
    public void sendAlert(Exception error, HttpServletRequest request) throws IOException {

        // 메시지 내용인 LayoutBlock List 생성
        List<LayoutBlock> layoutBlocks = generateLayoutBlock(error, request);

        // 슬랙의 send API과 webhookURL을 통해 생성한 메시지 내용 전송
        Slack.getInstance().send(errorUrl, WebhookPayloads
                .payload(p ->
                        // 메시지 전송 유저명
                        p.username("Exception is detected 🚨")
                                // 메시지 전송 유저 아이콘 이미지 URL
                                .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                                // 메시지 내용
                                .blocks(layoutBlocks)));
    }

    public void sendUserAlert(User user) throws IOException {

        // 메시지 내용인 LayoutBlock List 생성
        List<LayoutBlock> layoutBlocks = generateUserLayoutBlock(user);

        // 슬랙의 send API과 webhookURL을 통해 생성한 메시지 내용 전송
        Slack.getInstance().send(userUrl, WebhookPayloads
                .payload(p ->
                        // 메시지 전송 유저명
                        p.username("Register is detected 🐣")
                                // 메시지 전송 유저 아이콘 이미지 URL
                                .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                                // 메시지 내용
                                .blocks(layoutBlocks)));
    }

    public void sendRoomAlert(Room room) throws IOException {

        // 메시지 내용인 LayoutBlock List 생성
        List<LayoutBlock> layoutBlocks = generateRoomLayoutBlock(room);

        // 슬랙의 send API과 webhookURL을 통해 생성한 메시지 내용 전송
        Slack.getInstance().send(userUrl, WebhookPayloads
                .payload(p ->
                        // 메시지 전송 유저명
                        p.username("Event is created 🎉")
                                // 메시지 전송 유저 아이콘 이미지 URL
                                .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                                // 메시지 내용
                                .blocks(layoutBlocks)));
    }

    public void sendCelebAlert(Celeb celeb) throws IOException {

        // 메시지 내용인 LayoutBlock List 생성
        List<LayoutBlock> layoutBlocks = generateCelebLayoutBlock(celeb);

        // 슬랙의 send API과 webhookURL을 통해 생성한 메시지 내용 전송
        Slack.getInstance().send(userUrl, WebhookPayloads
                .payload(p ->
                        // 메시지 전송 유저명
                        p.username("Celeb Note is attached 🥳")
                                // 메시지 전송 유저 아이콘 이미지 URL
                                .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                                // 메시지 내용
                                .blocks(layoutBlocks)));
    }

    // 전체 메시지가 담긴 LayoutBlock 생성
    private List generateLayoutBlock(Exception error, HttpServletRequest request) {
        return Blocks.asBlocks(
                getHeader("서버 측 오류로 예상되는 예외 상황이 발생하였습니다."),
                Blocks.divider(),
                getSection(generateErrorMessage(error)),
                Blocks.divider(),
                getSection(generateErrorPointMessage(request)),
                Blocks.divider(),
                // 이슈 생성을 위해 프로젝트의 Issue URL을 입력하여 바로가기 링크를 생성
                getSection("<https://github.com/SOPT33-SOPKATHON/Server/issues|이슈 생성하러 가기>")
        );
    }

    private List generateUserLayoutBlock(User user) {
        return Blocks.asBlocks(
                getHeader("새로운 유저가 회원가입을 완료했습니다!"),
                Blocks.divider(),
                getSection(generateUserCountMessage(user)),
                Blocks.divider(),
                getSection(generateUserMessage(user))
        );
    }

    private List generateRoomLayoutBlock(Room room) {
        return Blocks.asBlocks(
                getHeader("유저가 이벤트를 생성 했습니다!"),
                Blocks.divider(),
                getSection(generateRoomUserMessage(room)),
                Blocks.divider(),
                getSection(generateRoomMessage(room))
        );
    }

    private List generateCelebLayoutBlock(Celeb celeb) {
        return Blocks.asBlocks(
                getHeader("유저가 축하노트를 남겼습니다!"),
                Blocks.divider(),
                getSection(generateCelebRoomMessage(celeb)),
                Blocks.divider(),
                getSection(generateCelebMessage(celeb))
        );
    }

    // 예외 정보 메시지 생성
    private String generateErrorMessage(Exception error) {
        sb.setLength(0);
        sb.append("*[🔥 Exception]*" + NEW_LINE + error.toString() + DOUBLE_NEW_LINE);
        sb.append("*[📩 From]*" + NEW_LINE + readRootStackTrace(error) + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    // HttpServletRequest를 사용하여 예외발생 요청에 대한 정보 메시지 생성
    private String generateErrorPointMessage(HttpServletRequest request) {
        sb.setLength(0);
        sb.append("*[🧾세부정보]*" + NEW_LINE);
        sb.append("Request URL : " + request.getRequestURL().toString() + NEW_LINE);
        sb.append("Request Method : " + request.getMethod() + NEW_LINE);
        sb.append("Request Time : " + new Date() + NEW_LINE);

        return sb.toString();
    }

    // 예외발생 클래스 정보 return
    private String readRootStackTrace(Exception error) {
        return error.getStackTrace()[0].toString();
    }

    private String generateUserMessage(User user) {
        sb.setLength(0);
        sb.append("*[👀 닉네임]*" + NEW_LINE + user.getNickname() + DOUBLE_NEW_LINE);
        sb.append("*[📩 KAKAO ID]*" + NEW_LINE + user.getSocialId().toString() + DOUBLE_NEW_LINE);
        sb.append("*[📷 프로필 사진]*" + NEW_LINE + user.getProfileImage() + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    private String generateUserCountMessage(User user) {
        sb.setLength(0);
        sb.append("*[🏆 누적 회원가입 수]*" + NEW_LINE + user.getId().toString() + " 명" + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    private String generateRoomMessage(Room room) {
        sb.setLength(0);
        sb.append("*[🎉 이벤트 이름]*" + NEW_LINE + room.getRoomName() + DOUBLE_NEW_LINE);
        sb.append("*[💬 설명]*" + NEW_LINE + room.getRoomContent() + DOUBLE_NEW_LINE);
        sb.append("*[⏰ 일시]*" + NEW_LINE + room.getTime() + DOUBLE_NEW_LINE);
        sb.append("*[✨ 이벤트 링크]*" + NEW_LINE + "https://cong-ratulation.vercel.app/event/" + room.getRoomUUID() + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    private String generateRoomUserMessage(Room room) {
        sb.setLength(0);
        sb.append("*[👀 유저 닉네임]*" + NEW_LINE + room.getUser().getNickname() + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    private String generateCelebMessage(Celeb celeb) {
        sb.setLength(0);
        sb.append("*[👀 유저 닉네임]*" + NEW_LINE + celeb.getNickname() + DOUBLE_NEW_LINE);
        sb.append("*[💬 축하노트 내용]*" + NEW_LINE + celeb.getCelebContent() + DOUBLE_NEW_LINE);
        sb.append("*[⏰ 남긴 시간]*" + NEW_LINE + celeb.getPostTime() + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    private String generateCelebRoomMessage(Celeb celeb) {
        sb.setLength(0);
        sb.append("*[🎉 이벤트 이름]*" + NEW_LINE + celeb.getRoom().getRoomName() + DOUBLE_NEW_LINE);
        sb.append("*[✨ 이벤트 링크]*" + NEW_LINE + "https://cong-ratulation.vercel.app/event/" + celeb.getRoom().getRoomUUID() + DOUBLE_NEW_LINE);

        return sb.toString();
    }

    // 에러 로그 메시지의 제목 return
    private LayoutBlock getHeader(String text) {
        return Blocks.header(h -> h.text(
                plainText(pt -> pt.emoji(true)
                        .text(text))));
    }

    // 에러 로그 메시지 내용 return
    private LayoutBlock getSection(String message) {
        return Blocks.section(s ->
                s.text(BlockCompositions.markdownText(message)));
    }
}