package com.example.laboratoryreservationsystem.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.laboratoryreservationsystem.exception.Code;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String msg;
    private Object data;

    //增加一个空容器，每次成功只返回它就够了
    private static final ResultVO EMPTY = ResultVO.builder()
            .code(200)
            .build();

    //只返回成功的状态码
    public static ResultVO ok(){
        return EMPTY;
    }

    public static ResultVO success(Object data) {

        return ResultVO.builder()
                .code(200)
                .data(data)
                .build();
    }
    //非手动
    public static ResultVO fail(Code code) {

        return ResultVO.builder()
                .code(code.getCode())
                .msg(code.getMessage())
                .build();
    }
    //手动
    public static ResultVO error(int code,String msg) {
        return ResultVO.builder()
                .code(code)
                .msg(msg)
                .build();
    }

}
