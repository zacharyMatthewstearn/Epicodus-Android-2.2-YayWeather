package com.example.guest.yayweather;

/**
 * Created by Guest on 11/30/16.
 */
public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/PWChristmasfont.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/PWChristmasfont.ttf");
    }
}
