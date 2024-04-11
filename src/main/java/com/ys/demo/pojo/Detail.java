package com.ys.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail{
    private Integer port_scan;
    private Integer jarm_hash;
    private Integer web_info;
    private Integer cyberspace;
    private Integer monitor;
    private Integer cluster;
    private String error;
}