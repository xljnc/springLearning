package com.wt.test.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: qiyu
 * @date: 2020/5/13 10:59
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FirstResponseDTO implements Serializable {

    private String name;

    private Integer age;
}
