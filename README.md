# liwyfinalutils自定义导航栏、底部菜单栏、图片缓存、支持下拉刷新的webview等控件
        1、导航栏使用如下：
        liwyTop = (LiwyTop)findViewById(R.id.liwytop);
        //配置类(配置控件样式,可配置详情可查看ConfigTop类)
        ConfigTop config = new ConfigTop();
        config.setLeftButtonImg(R.mipmap.ic_launcher);//设置左按钮图片
        //需先传入ConfigTop的实例对象.然后再调用setLiwyTop方法初始化标题栏.
        liwyTop.setConfig(config);
        //左按钮文字,传Null的话不显示;标题文字;右按钮文字,传Null的话不显示
        liwyTop.setLiwyTop("返回","首页","提交");
        // 传入当前类实例对象,设置默认左按钮点击事件:销毁当前页面
        liwyTop.setAppCompatActivity(this);
        
        2、底部菜单栏
        private void initView(){
          liwyIndicator = (LiwyIndicator)findViewById(R.id.liwyTabIndicator);
          // 需要结合fragment可以调用liwyIndicator.setTabAndViewPager（tabs,viewpager），传入实现的viewpager即可
          liwyIndicator.setTabs(tabs);
          // 底部按钮的点击事件
          liwyIndicator.setmOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onClick(View v) {
                LiwyIndicator.TabView tab = (LiwyIndicator.TabView)v;
                Toast.makeText(TabActivity.this,"点击了" + tab.getIndex(),Toast.LENGTH_SHORT).show();
            }
        });
      }

      private void initTabs(){
        tabs = new ArrayList<TabBean>();
        TabBean t1 = new TabBean("测试1",R.mipmap.app_aligame,R.mipmap.ic_launcher);
        TabBean t2 = new TabBean("测试2",R.mipmap.app_coupon,R.mipmap.ic_launcher);
        TabBean t3 = new TabBean("测试3",R.mipmap.app_exchange,R.mipmap.ic_launcher);
        tabs.add(t1);
        tabs.add(t2);
        tabs.add(t3);
      }
