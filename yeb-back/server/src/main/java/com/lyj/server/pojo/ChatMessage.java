package com.lyj.server.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {
    private String from;
    private String to;
    private String content;
    private Date date;
    private String fromNickname;

}
