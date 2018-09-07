package cn.izouxiang.bezierdemo;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import cn.izouxiang.bezierdemo.bezier.BezierView;

public class MainActivity extends AppCompatActivity {
    private BezierView mBezierView;
    private EditText mEditText;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBezierView = (BezierView) findViewById(R.id.bezier);
        mEditText = (EditText) findViewById(R.id.editText);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        final List<PointF> pointList = new ArrayList<>();
        pointList.add(new PointF(0,0));
        pointList.add(new PointF(45,100));//55;
        pointList.add(new PointF(120,0));//75
        pointList.add(new PointF(230,100));//100
        pointList.add(new PointF(355,0));//125
        pointList.add(new PointF(505,100));//150
        pointList.add(new PointF(680,0));//175
        pointList.add(new PointF(880,100));//200
        pointList.add(new PointF(1105,0));//225
        mBezierView.setPointList(pointList);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for(int j = 0; j < pointList.size(); j++ ){
                    PointF pointF = pointList.get(j);
                    pointF.y = j % 2 == 0 ? 0 : 100 * i / 100;
                }
                mBezierView.setPointList(pointList);
                mBezierView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
    public void onClick(View v){
        try {
            mBezierView.setLineSmoothness(Float.valueOf(mEditText.getText().toString()));
        } catch (NumberFormatException e) {
        }
    }
}
