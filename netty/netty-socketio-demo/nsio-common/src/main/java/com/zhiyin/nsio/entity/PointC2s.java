package com.zhiyin.nsio.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointC2s implements Serializable {

    private double lon;

    private double lat;

}