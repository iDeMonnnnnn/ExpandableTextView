package com.demon.expandabletextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val str = "新西兰总理杰辛达阿德恩(Jacinda Ardern)表示，新西兰的华人社区是新西兰历史最悠久、规模最大的社区之一。她说：“多年来，中国新年已经成为许多新西兰人庆祝的一个重要节日。鼠年意味着好运，鼠年出生的人被认为" +
            "是刻苦、节俭又勤奋的人。”在ASB Showgrounds循例举行的新春花市同乐日活动中，阿德恩和中国驻新西兰大使吴玺将按照华人习俗点睛醒狮，为庆祝中国新年的诸多活动拉开帷幕。" +
            "2020年的中国农历新年初一是1月25日，                   " +
            "之后将有为期15天的庆祝活动，包括街头庆祝活动、社区活动以及炮竹和烟花表演。\n\n多年来，为期4天的元宵灯节已成为新西兰最大的文化活动，本年度的元宵灯节将在2月13日至16日举行，为庆祝鼠年新年画上句号。"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        expandTextView1.setText("新西兰总理杰辛达阿德恩(Jacinda Ardern)表示，新西兰的华人社区是新西兰历史最悠久、规模最大的社区之一。")


        expandTextView2.setText(str)
    }
}
