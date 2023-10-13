package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//Information about members portfolio, Used for the portfolio page

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Cv {
    private int id;
    private long memberId;
    private List<CvItems> items;
}
