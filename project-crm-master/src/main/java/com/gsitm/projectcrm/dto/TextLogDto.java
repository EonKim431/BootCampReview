package com.gsitm.projectcrm.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TextLogDto {
   private String CUS_NM; //고객명
   private String MAN_NM; //매니져명
   private String DATE; // 
   private String HOUR; //시간
   private String CONTENT; //콘텐츠
}