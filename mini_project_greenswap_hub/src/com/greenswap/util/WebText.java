package com.greenswap.util;

public final class WebText {

    private WebText() {
    }

    public static String escapeHtml(String value) {
        if (value == null) {
            return "";
        }

        String escaped = value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
        return escaped;
    }
}
