package com.example.infiniteScrollingOfLists.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoData {
    private String fileName;
    private Double fileSizeFromByte;
    private String videoBytes;
}