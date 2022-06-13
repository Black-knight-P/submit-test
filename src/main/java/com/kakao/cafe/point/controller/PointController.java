package com.kakao.cafe.point.controller;

import com.kakao.cafe.global.dto.CustomResponse;
import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import com.kakao.cafe.point.dto.PointDto;
import com.kakao.cafe.point.service.PointService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @GetMapping
    @ApiOperation(value = "고객 잔여 포인트 조회 API", notes = "고객의 잔여 포인트를 조회한다.")
    public PointDto getMyPoint(@RequestParam(name = "userId") String userId) throws Exception {
        log.debug("My Point Request : {} ", userId);
        Integer balance = pointService.getBalance(userId);
        return PointDto.builder().userId(userId).points(balance).build();
    }

    @PostMapping("/charge")
    @ApiOperation(value = "포인트 충전하기 API", notes = "고객의 포인트를 충전한다.")
    public CustomResponse charge(@RequestBody @Valid PointDto pointDto) throws Exception {
        log.debug("Charge Point Request : {} ", pointDto.toString());
        pointService.charge(pointDto.getUserId(), pointDto.getPoints());
        return CustomResponse.createNormalResponse();
    }

}
