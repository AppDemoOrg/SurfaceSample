# SurfaceViewDemo   

实现SurfaceView播放Mp4   

## SurfaceView双缓冲原理      
SurfaceView的双缓冲机制前/后台缓冲区交替成为后/前台缓冲区。      
同时需要注意每次在绘制的时候都需要清除Canvas画布，不然会出现画面叠加的现象。      

那么这样有什么好处呢？      
1、提高渲染效率    
2、可以避免刷新频率过高而出现的闪烁现象     
