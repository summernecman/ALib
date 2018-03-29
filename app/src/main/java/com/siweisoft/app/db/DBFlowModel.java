package com.siweisoft.app.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class) //上面自己创建的类（定义表的名称 版本）
public class DBFlowModel extends BaseModel {

    @PrimaryKey(autoincrement = true) //主键  //autoincrement 开启自增
    public int id;
    @Column               //表示一栏 一列
    public  String name; //自己需要存储的字段 
    @Column
    public int age;
    @Column
    public String address;
    @Column
    public int phone;

    @Override
    public String toString() {
        return "DBFlowModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}