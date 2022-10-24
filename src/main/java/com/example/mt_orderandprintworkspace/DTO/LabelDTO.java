package com.example.mt_orderandprintworkspace.DTO;

import java.awt.image.BufferedImage;

public class LabelDTO {
    private BufferedImage image;
    private String name;
    private String serialNumber;

    public LabelDTO() {
    }

    public LabelDTO(BufferedImage image, String name, String serialNumber) {
        this.image = image;
        if (name.length()<=12) {
            this.name = name.toLowerCase();
        } else {
            this.name = name.substring(0,12).toLowerCase();
        }
        this.serialNumber = serialNumber;
    }

    public String getImageAsBase64String() {
        //TODO затычка. заменить на выдачу реального base64-кодированного изображения
        return "iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAAAAAAfl4auAAAA+UlEQVR4XtWNQY7DMAwD8/9Ppwe3ozEtBEW3WaM8GCTFSY7jOM7z/Phtqvffpnr/7fk1Du9YbbxrHP6r8KiY2uB5jWyHablhrsg4RHMz7NbvhZhthKOyGf6i3wgjT9feWMPvgKmI9nFybKr/gseUtg66RqzSodp5HbFKh2rndcSpbIFR1nbelOLgdaHfhz1yEwPM9K19cLSxtp7rEAyG3jJSgsHQW0ZKMN7F2pFNHcRugeM8TMQnxjQOsY4o9I8wihs+yv4TO2BXeIsZb8kjvMWMt+QR3mLGO6mpXoLX15afT1m6Dcb5UOeX+tMmOAB8AOuyqfD3wz+oBzUUVv5hDdjMAAAAAElFTkSuQmCC";
    }

    public String getNameFirst() {
        if (name.length()<=6) {
            return name;
        } else {
            return name.substring(0,6);
        }
    }

    public String getNameSecond() {
        if (name.length()<=6) {
            return "";
        } else {
            return name.substring(6,name.length());
        }
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
