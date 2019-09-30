package com.utvgo.huya.diff;

import android.content.Context;

public class VoiceAssistant {
    public interface VoiceFeedBack
    {
        void actionOnString(final String cmd);
    }

    public void start(final Context context, final VoiceFeedBack feedBack)
    {

    }

    public void stop()
    {

    }

}
