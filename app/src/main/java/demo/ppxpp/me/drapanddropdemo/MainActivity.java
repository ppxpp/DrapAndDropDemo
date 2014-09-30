package demo.ppxpp.me.drapanddropdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements View.OnLongClickListener {

    private Button btn1, btn2, btn3;
    private TextView label;
    private ImageView imageView;

    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        label = (TextView) findViewById(R.id.text1);
        label.setOnLongClickListener(this);
        label.setOnDragListener(dragListener);

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setTag("this is image tag");
        //imageView.setOnLongClickListener(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnDragListener(dragListener);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnDragListener(dragListener);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnDragListener(dragListener);
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item(((TextView) v).getText().toString());
        ClipData data = new ClipData("label", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
        View.DragShadowBuilder dsb = new View.DragShadowBuilder(v);
        v.startDrag(data, dsb, null, 0);
        return true;
    }

    private View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final int action = event.getAction();
            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType("text/plain")){
                        Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_STARTED return true for " + v);
                        return true;
                    }
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_STARTED return false for " + v);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_ENTERED return true for " + v);
                    AnimatorSet set = new AnimatorSet();
                    ObjectAnimator animator1 = ObjectAnimator.ofInt(v, "left", v.getLeft(), v.getLeft() - 30);
                    ObjectAnimator animator2 = ObjectAnimator.ofInt(v, "right", v.getRight(), v.getRight() + 30);
                    set.play(animator1).with(animator2);
                    set.setDuration(500);
                    set.start();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_LOCATION return true for " + v);
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    AnimatorSet set1 = new AnimatorSet();
                    ObjectAnimator animator3 = ObjectAnimator.ofInt(v, "left", v.getLeft(), v.getLeft() + 30);
                    ObjectAnimator animator4 = ObjectAnimator.ofInt(v, "right", v.getRight(), v.getRight() - 30);
                    set1.play(animator4).with(animator3);
                    set1.setDuration(500);
                    set1.start();
                    /*v.setLeft(v.getLeft() + 30);
                    v.setRight(v.getRight() - 30);*/
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_EXITED return true for " + v);
                    return true;
                case DragEvent.ACTION_DROP:
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DROP return true for " + v);
                    AnimatorSet set2 = new AnimatorSet();
                    ObjectAnimator animator33 = ObjectAnimator.ofInt(v, "left", v.getLeft(), v.getLeft() + 30);
                    ObjectAnimator animator44 = ObjectAnimator.ofInt(v, "right", v.getRight(), v.getRight() - 30);
                    set2.play(animator44).with(animator33);
                    set2.setDuration(500);
                    set2.start();
                    /*v.setLeft(v.getLeft() + 30);
                    v.setRight(v.getRight() - 30);*/
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    //btn1.setText(btn1.getText().toString() + item.getText());
                    ((TextView)v).setText(item.getText() + "_" + flag++);
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("ACTION_DRAG_STARTED", "ACTION_DRAG_ENDED return false for " + v);
                    break;
            }
            return false;
        }
    };

}
