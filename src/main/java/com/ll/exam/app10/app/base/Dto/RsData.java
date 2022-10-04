package com.ll.exam.app10.app.base.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RsData<T> {

    private final String resultCode;
    private final String msg;
    private final T body;

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }


    public boolean isFail() {
        return resultCode.startsWith("F-");
    }
}
