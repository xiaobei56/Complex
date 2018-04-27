package cn.gridlife.xiaobei.bzbtoolsapp.functions.two;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by BZB on 2018/4/25.
 * Project: Complex.
 * Package：cn.gridlife.xiaobei.bzbtoolsapp.functions.two.
 */

class MultipleItem implements MultiItemEntity {
    public static final int TYPE0 = 0;
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;
    public static final int TYPE4 = 4;
    private Type0Bean type0Bean;
    private Type1Bean type1Bean;
    private Type2Bean type2Bean;
    private Type3Bean type3Bean;
    private Type4Bean type4Bean;
    private int type;

    public MultipleItem() {
    }

    public MultipleItem(Type1Bean type1Bean, Type2Bean type2Bean, Type3Bean type3Bean, Type4Bean type4Bean, int type) {
        this.type1Bean = type1Bean;
        this.type2Bean = type2Bean;
        this.type3Bean = type3Bean;
        this.type4Bean = type4Bean;
        this.type = type;
    }

    public Type0Bean getType0Bean() {
        return type0Bean;
    }

    public void setType0Bean(Type0Bean type0Bean) {
        this.type0Bean = type0Bean;
    }

    public Type1Bean getType1Bean() {
        return type1Bean;
    }

    public void setType1Bean(Type1Bean type1Bean) {
        this.type1Bean = type1Bean;
    }

    public Type2Bean getType2Bean() {
        return type2Bean;
    }

    public void setType2Bean(Type2Bean type2Bean) {
        this.type2Bean = type2Bean;
    }

    public Type3Bean getType3Bean() {
        return type3Bean;
    }

    public void setType3Bean(Type3Bean type3Bean) {
        this.type3Bean = type3Bean;
    }

    public Type4Bean getType4Bean() {
        return type4Bean;
    }

    public void setType4Bean(Type4Bean type4Bean) {
        this.type4Bean = type4Bean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    public int getItemType() {
        return type;
    }
}
