package com.utvgo.handsome.diff;

import android.content.Context;
import android.content.Intent;

import com.iflytek.xiri.Feedback;
import com.iflytek.xiri.scene.ISceneListener;
import com.iflytek.xiri.scene.Scene;

public class GDTVXiri extends VoiceAssistant {
    private Scene focusScene = null;
    private Feedback feedback = null;

    @Override
    public void start(final Context context, final VoiceFeedBack fb) {
        if(fb != null)
        {
            if(this.feedback == null)
            {
                this.feedback = new Feedback(context);
            }
            if(focusScene == null)
            {
                focusScene = new Scene(context);
                focusScene.init(new ISceneListener() {
                    @Override
                    public String onQuery() {
                        return "{\"_scene\":\"com.utvgo.qqmusic.mv\",\"_commands\":{\"PREVIOUS\":[\"上一首\"],\"NEXT\":[\"下一首\"]}}";
                    }

                    @Override
                    public void onExecute(Intent intent) {
                        feedback.begin(intent);
                        if (intent.hasExtra("_scene")) {
                            String command = intent.getStringExtra("_command");
                            fb.actionOnString(command);
                            switch (command) {
                                case "PREVIOUS":
                                    feedback.feedback("播放上一首", Feedback.SILENCE);
                                    break;
                                case "NEXT":
                                    feedback.feedback("播放下一首", Feedback.SILENCE);
                                    break;
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void stop()
    {
        try {
            if(focusScene != null)
            {
                focusScene.release();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
