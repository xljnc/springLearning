package com.wt.test.spring.controller;

import com.wt.test.spring.dto.FirstRequestDTO;
import com.wt.test.spring.dto.FirstResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qiyu
 * @date: 2020/5/13 11:03
 * @description:
 */
@RestController
@RequestMapping("/first")
public class FirstController {

    @PostMapping("/one")
    public FirstResponseDTO getFirstResponseDTO(@RequestBody FirstRequestDTO requestDTO) {
        return FirstResponseDTO.builder()
                .name(requestDTO.getName()).age(requestDTO.getAge()).build();
    }
}
