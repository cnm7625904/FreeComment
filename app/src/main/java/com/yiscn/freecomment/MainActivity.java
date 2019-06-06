package com.yiscn.freecomment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    private myAdapter adapter;
    private RelativeLayout rl;
    private EditText et;
    private View ll;
    private int y1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        rv=findViewById(R.id.rv);
        rl=findViewById(R.id.rl);
        linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        adapter=new myAdapter(R.layout.item_tv,null);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        View viewfooter = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) rv.getParent(), false);
        adapter.setFooterView(viewfooter);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        adapter.addData(getData());
        initKeyboardHeightObserver();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ll=adapter.getViewByPosition(rv,position,R.id.ll);
                int[] viewLocation=new int[2];
                viewLocation[0] = 0;
                viewLocation[1] = 0;
                ll.getLocationInWindow(viewLocation);
                int[] etLocation=new int[2];
                etLocation[0] = 0;
                etLocation[1] = 0;
                et.getLocationInWindow(etLocation);
                Log.e("View距顶部高度",viewLocation[1]+"---");
                Log.e("View的自身高度",ll.getHeight()+"---");
                y1=viewLocation[1];
                int id=view.getId();
                switch (id){
                    case R.id.btn:
                        rl.setVisibility(View.VISIBLE);
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.requestFocus();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                        break;
                    default:
                        break;
                }
            }
        });

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                rl.setVisibility(View.GONE);
            }
        });

    }
    private void initKeyboardHeightObserver() {
        KeyboardControlMnanager.observerKeyboardVisibleChange(MainActivity.this, new KeyboardControlMnanager.OnKeyboardStateChangeListener() {
            @Override
            public void onKeyboardChange(int keyboardHeight, boolean isVisible) {
                if (isVisible) {
                    Log.e("键盘高度",keyboardHeight+"??"+et.getHeight());
                    int[] viewLocation=new int[2];
                    viewLocation[0] = 0;
                    viewLocation[1] = 0;
                    ll.getLocationOnScreen(viewLocation);
                    int[] etLocation=new int[2];
                    etLocation[0] = 0;
                    etLocation[1] = 0;
                    et.getLocationOnScreen(etLocation);
                    Log.e("输入框距顶部高度",etLocation[1]+"---");
                    rv.smoothScrollBy(0,y1+ll.getHeight()-etLocation[1]
                    );
                    Log.e("滑动",(y1+ll.getHeight()-etLocation[1])+"---");
                } else {
                    rl.setVisibility(View.GONE);
                }
            }
        });
    }
    private List<String> getData(){
        List<String> list=new ArrayList<>();
        list.add("RxLife终极进化，一行代码解决RxJava内存泄漏");
        list.add("ViewPager2：官方Viewpager升级版来临");
        list.add("翰墨功名里，江山富贵人。倏看双鸟下，已负百年身。江清风偃木，霜落雁横空。若个丹青里，犹须著此翁。晴野下田收。照影寒江落雁洲。禅榻茶炉深闭阁，飕飕。横雨旁风不到头。登览却轻酬。剩作新诗报答秋。人意自阑花自好，休休。今日看时蝶也愁。");
        list.add("暑雨不作凉，爽风只自高。\n" +
                "我老亦衰疾，奈此正郁陶。\n" +
                "魏侯有新语，高处近风骚。\n" +
                "隐几聆五字，未觉历日劳。\n" +
                "酒然堕冰井，起粟竖寒毛。\n" +
                "三山已在目，万象谁能逃。\n" +
                "历险见绝足，过口味豚膏。\n" +
                "愿为夏雷鸣，莫作饥鸢号。");
        list.add("张侯便然腹如鼓，饥雷收声酒如雨。\n" +
                "读书不计有余处，尚著我辈千百许。\n" +
                "翻湖倒海不作难，将军百战富善贾。\n" +
                "弟子不必不如师，欲知其人视其主。\n" +
                "秋来待试丞相府，榖马砺兵吾甚武。\n" +
                "商周不敌闻其语，一战而霸在此举。\n" +
                "百年富贵要自取，人将公卿还尔汝，\n" +
                "德如墨君谁敢侮。");
        list.add("欲落未落雪迫人，将尽不尽冬压春。\n" +
                "风枝冰瓦有去鸟，远坊穷巷无来人。\n" +
                "忽闻叩门声遽速，惊鸡透篱犬升屋。\n" +
                "使君传教赐薪炭，妓围那解思寒谷。\n" +
                "老身曲直不足云，冷窗冻壁作春温。\n" +
                "定知和气家家到，不独先生席作门。");
        list.add("杨子之邻人亡羊，既率其党，又请杨子之竖追之。杨子曰：“嘻！亡一羊，何追者之众？”邻人曰：“多歧路。”既反，问：“获羊乎？”曰：“亡之矣。”曰：“奚亡之？”曰：“歧路之中又有歧焉，吾不知所之，所以反也。”杨子戚然变容，不言者移时，不笑者竟日。\n" +
                "门人怪之，请曰：“羊，贱畜，又非夫子之有，而损言笑者，何哉？”杨子不答，门人不获所命。\n" +
                "弟子孟孙阳出，以告心都子。心都子他日与孟孙阳偕入而问曰：“昔有昆弟三人，游齐、鲁之间，同师而学，进仁义之道而归。其父曰：‘仁义之道若何？’伯曰：‘仁义使我爱身而后名。’仲曰：‘仁义使我杀身以成名。’叔曰：‘仁义使我身名并全。’彼三术相反，而同出于儒。孰是孰非邪？”\n" +
                "杨子曰：“人有滨河而居者，习于水，勇于泅，操舟鬻渡，利供百口，裹粮就学者成徒，而溺死者几半。本学泅不学溺，而利害如此。若以为孰是孰非？”心都子嘿然而出。\n" +
                "孟孙阳让之曰：“何吾子问之迂，夫子答之僻？吾惑愈甚。”\n" +
                "心都子曰：“大道以多歧亡羊，学者以多方丧生。学非本不同，非本不一，而末异若是。唯归同反一，为亡得丧。子长先生之门，习先生之道，而不达先生之况也，哀哉！”");
        list.add("高节长身老不枯，平生风骨自清癯。爱君修竹为尊者，却笑寒松作大夫。\n" +
                "未见同参木上座，空余听法石於菟。戏将秋色分斋钵，抹月批风得饱无？");
        return list;

    }


    class myAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public myAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);

        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView textView=helper.getView(R.id.tv_1);
            helper.addOnClickListener(R.id.btn);
            textView.setText(item);
        }
    }

}
