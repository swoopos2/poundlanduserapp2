package com.poundland.retail.decentslider;

import android.view.View;

import com.poundland.retail.enumeratio.FileType;


public class DecentSliderModel {
    View view;
    String localImage;
    String imageUrl;
    FileType fileType;

    public DecentSliderModel() {
    }

    public DecentSliderModel(View view, String localImage, String imageUrl, FileType fileType) {
        this.view = view;
        this.localImage = localImage;
        this.imageUrl = imageUrl;
        this.fileType = fileType;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getLocalImage() {
        return localImage;
    }

    public void setLocalImage(String localImage) {
        this.localImage = localImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "DecentSliderModel{" +
                "view=" + view +
                ", localImage='" + localImage + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fileType=" + fileType +
                '}';
    }
}
