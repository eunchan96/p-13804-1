package com.back.standard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Pageable {
    private final int pageNum;
    private final int pageSize;

    public long getSkipCount() {
        return (long) (pageNum - 1) * pageSize;
    }
}
