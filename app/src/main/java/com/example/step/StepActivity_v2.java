package com.example.step;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 8, 0},
        k = 1,
        d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\fH\u0014J\u0012\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0006\u0010\u0019\u001a\u00020\fJ\b\u0010\u001a\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"},
        d2 = {"Lcom/example/step/StepActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/hardware/SensorEventListener;", "()V", "previousTotalSteps", "", "running", "", "sensorManager", "Landroid/hardware/SensorManager;", "totalSteps", "loadData", "", "onAccuracyChanged", "sensor", "Landroid/hardware/Sensor;", "accuracy", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "resetSteps", "saveData", "app_debug"}
)
public final class StepActivity_v2 extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean running;
    private float totalSteps;
    private float previousTotalSteps;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(1300000);
        this.loadData();
        this.resetSteps();
        Object var10001 = this.getSystemService("sensor");
        if (var10001 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.hardware.SensorManager");
        } else {
            this.sensorManager = (SensorManager)var10001;
        }
    }

    protected void onResume() {
        super.onResume();
        this.running = true;
        SensorManager var10000 = this.sensorManager;
        Sensor stepSensor = var10000 != null ? var10000.getDefaultSensor(19) : null;
        if (stepSensor == null) {
            Toast.makeText((Context)this, (CharSequence)"No sensor detected on this device", 0).show();
        } else {
            var10000 = this.sensorManager;
            if (var10000 != null) {
                var10000.registerListener((SensorEventListener)this, stepSensor, 2);
            }
        }

    }

    public void onSensorChanged(@Nullable SensorEvent event) {
        TextView tv_stepsTaken = (TextView)this.findViewById(1000000);
        if (this.running) {
            Intrinsics.checkNotNull(event);
            this.totalSteps = event.values[0];
            int currentSteps = (int)this.totalSteps - (int)this.previousTotalSteps;
            Intrinsics.checkNotNullExpressionValue(tv_stepsTaken, "tv_stepsTaken");
            tv_stepsTaken.setText((CharSequence)String.valueOf(currentSteps));
        }

    }

    public final void resetSteps() {
        final Ref.ObjectRef tv_stepsTaken = new Ref.ObjectRef();
        tv_stepsTaken.element = (TextView)this.findViewById(1000000);
        ((TextView)tv_stepsTaken.element).setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                Toast.makeText((Context) StepActivity_v2.this, (CharSequence)"Long tap to reset steps", 0).show();
            }
        }));
        ((TextView)tv_stepsTaken.element).setOnLongClickListener((View.OnLongClickListener)(new View.OnLongClickListener() {
            public final boolean onLongClick(View it) {
                StepActivity_v2.this.previousTotalSteps = StepActivity_v2.this.totalSteps;
                TextView var10000 = (TextView)tv_stepsTaken.element;
                Intrinsics.checkNotNullExpressionValue(var10000, "tv_stepsTaken");
                var10000.setText((CharSequence)String.valueOf(0));
                StepActivity_v2.this.saveData();
                return true;
            }
        }));
    }

    private final void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("myPrefs", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", this.previousTotalSteps);
        editor.apply();
    }

    private final void loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("myPrefs", 0);
        float savedNumber = sharedPreferences.getFloat("key1", 0.0F);
        Log.d("MainActivity", String.valueOf(savedNumber));
        this.previousTotalSteps = savedNumber;
    }

    public void onAccuracyChanged(@Nullable Sensor sensor, int accuracy) {
    }

    // $FF: synthetic method
    public static final float access$getPreviousTotalSteps$p(StepActivity_v2 $this) {
        return $this.previousTotalSteps;
    }

    // $FF: synthetic method
    public static final void access$setTotalSteps$p(StepActivity_v2 $this, float var1) {
        $this.totalSteps = var1;
    }
}
