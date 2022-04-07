package com.poundland.retail.enumeratio;

public enum FileType {
    IMAGE, VIDEO, AUDIO,UNDEFINED;

    public static FileType toEnum(String enumString) {
        try {
            return valueOf(enumString);
        } catch (Exception ex) {
            // For error cases
            return UNDEFINED;
        }
    }
}
