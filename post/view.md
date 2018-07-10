
### View 
* View：显示视图，内置画布，提供图形绘制函数、触屏事件、
按键事件函数等；必须在UI主线程内更新画面，速度较慢。

* SurfaceView：基于view视图进行拓展的视图类，
更适合2D游戏的开发；是view的子类，类似使用双缓机制，
在新的线程中更新画面,所以刷新界面速度比view快。
其缺点是不能做变形和动画，也不能随屏幕的变化而变化，
另外不能在其上面覆盖其它的SurfaceView。

* GLSurfaceView：是surfaceview的子类，在其基础上封装了egl
环境管理,以及render线程。专用于3D游戏开发的视图，OpenGL ES专用。

* TextureView：它也是继承自View，只能运行中硬件加速窗口。
它的功能类似于SurfaceView + SurfaceTexture，它内部包含一个
SurfaceTexture，它可以让Camera的数据和显示分离，
比如需要做二次处理时，如Camera把采集的数据发送给SurfaceTexture
（比如做个美颜），SurfaceTexture处理后传给TextureView显示。
TextureView可以做view的变形和动画。一般它是在主线程上做处理
（在Android 5.0引入渲染线程后，它是在渲染线程中做的）。


### 参考文献        
