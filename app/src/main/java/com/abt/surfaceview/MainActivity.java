package com.abt.surfaceview;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mPath;
    private Button mPlayBtn, mReplayBtn, mPauseBtn, mStopBtn;
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = findViewById(R.id.sv);
        mPath = findViewById(R.id.et_path);
        mPlayBtn = findViewById(R.id.bt_play);
        mReplayBtn = findViewById(R.id.bt_replay);
        mPauseBtn = findViewById(R.id.bt_pause);
        mStopBtn = findViewById(R.id.bt_stop);

        mPauseBtn.setOnClickListener(this);
        mPlayBtn.setOnClickListener(this);
        mReplayBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);

        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("holder被销毁了");
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                System.out.println("holder被创建了");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                System.out.println("holder的大小变化了");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play:
                play();
                break;
            case R.id.bt_replay:
                replay();
                break;
            case R.id.bt_stop:
                stop();
                break;
            case R.id.bt_pause:
                pause();
                break;
            default:
                break;
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if ("继续".equals(mPauseBtn.getText().toString().trim())) {
            mMediaPlayer.start();
            mPauseBtn.setText("暂停");
            return;
        }
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPauseBtn.setText("继续");
            return;
        }
    }

    /**
     * 重新播放
     */
    private void replay() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.seekTo(0);
            return;
        }
        play();
    }

    /**
     * 停止播放音乐
     */
    private void stop() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release(); // 记得释放资源
            mMediaPlayer = null;
            mPlayBtn.setEnabled(true);
        }
    }

    /**
     * 播放音乐
     */
    private void play() {
        String path = mPath.getText().toString().trim();
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                /* 设置Video影片以SurfaceHolder播放 */
                mMediaPlayer.setDisplay(mSurfaceView.getHolder());

                mMediaPlayer.setDataSource(path);
                mMediaPlayer.prepare(); // might take long! (for buffering, etc)
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mPlayBtn.setEnabled(true);
                    }
                } );

                mPlayBtn.setEnabled(false);
            } catch (Exception e) {
                Toast.makeText(this, "播放失败", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        }

    }

}
