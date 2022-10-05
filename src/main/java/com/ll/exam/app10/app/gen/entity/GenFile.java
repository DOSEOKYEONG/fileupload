package com.ll.exam.app10.app.gen.entity;

import com.ll.exam.app10.app.base.AppConfig;
import com.ll.exam.app10.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class GenFile extends BaseEntity {
    private String relTypeCode;
    private long relId;
    private String typeCode;
    private String type2Code;
    private String fileExtTypeCode;
    private String fileExtType2Code;
    private int fileSize;
    private int fileNo;
    private String fileExt;
    private String fileDir;
    private String originFileName;

    public String getFileName() {
        return getId() + "." + getFileExt();
    }

    public String getUrl() {
        return "/gen/" + getFileDir() + "/" + getFileName();
    }

    public String getDownloadUrl() {
        return "/download/gen/" + getId();
    }

    public void merge(GenFile genFile) {
        relTypeCode = genFile.getRelTypeCode();
        relId = genFile.getRelId();
        typeCode = genFile.getTypeCode();
        type2Code = genFile.getType2Code();
        fileExtTypeCode = genFile.getFileExtTypeCode();
        fileExtType2Code = genFile.getFileExtType2Code();
        fileSize = genFile.getFileSize();
        fileNo = genFile.getFileNo();
        fileExt = genFile.getFileExt();
        fileDir = genFile.getFileDir();
        originFileName = genFile.getOriginFileName();
    }

    public String getFilePath() {
        return AppConfig.GET_FILE_DIR_PATH + "/" + getFileDir() + "/" + getFileName();
    }
}
