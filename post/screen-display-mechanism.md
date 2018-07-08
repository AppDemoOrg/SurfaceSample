
### android屏幕显示刷新机制
在一个典型的显示系统中，一般包括CPU、GPU、display三个部分，     
CPU负责计算数据，把计算好数据交给GPU，GPU会对图形数据进行渲染，      
渲染好后放到buffer里存起来，然后display       
（有的文章也叫屏幕或者显示器）负责把buffer里的数据呈现到屏幕上。         
很多时候，我们可以把CPU、GPU放在一起说，那么就是包括2部分，          
CPU/GPU 和display。

* tearing：一个屏幕内的数据来自2个不同的帧，画面会出现撕裂感       
* jank：一个帧在屏幕上连续出现2次      
* lag：从用户体验来说，就是点击下去到呈现效果之间存在延迟      
* 屏幕刷新频率： 一秒内屏幕刷新多少次，       
或者说一秒内显示了多少帧的图像，屏幕扫描是从左到右，        
从上到下执行的。显示器并不是一整个屏幕一起输出的，      
而是一个个像素点输出的，我们看不出来，是因为速度太快了，       
人有视觉暂留，所以看不出来。

### single-buffer单缓存      
* screen tearing出现的必然性，tearing发生的原因是    
display读buffer同时，buffer被修改。     
* 实际上display的刷新频率是固定的，          
但是CPU/GPU写buffer的时间是不定的，         
所以tearing的产生几乎是必然的。                

### double-buffer
* 双缓冲技术，基本原理就是采用两块buffer。    
一块back buffer用于CPU/GPU后台绘制，       
另一块framebuffer则用于显示，当back buffer准备就绪后，      
它们才进行交换。不可否认，doublebuffering可以在很大程度上，     
降低screen tearing错误。

### triple-buffer
* 为了优化显示性能，android 4.1版本对Android Display系统进行了重构，      
实现了Project Butter，引入了三个核心元素，即VSYNC、      
Triple Buffer和Choreographer。
* 一句话总结，vync同步使得CPU/GPU充分利用了16ms时间，减少jank
* 三缓冲作用： 
简单的说在2个缓存区被GPU和display占据的时候，开辟一个缓冲区给CPU用，       
一般来说都是用双缓冲，需要的时候会开启3缓冲，三缓冲的好处就是使得动画更为流畅，      
但是会导致lag，从用户体验来说，就是点击下去到呈现效果会有延迟。      
所以默认不开三缓冲，只有在需要的时候自动开启 
* 一句话总结三缓冲有效利用了等待vysnc的时间,    
减少了jank，但是带来了lag

### 参考文献    
1、[android屏幕刷新显示机制](https://blog.csdn.net/litefish/article/details/53939882)       