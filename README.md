Compatibility for ClipboardManager, from API level 1.

Related post: [再谈 Android API 兼容性处理](http://www.liaohuqiu.net/cn/posts/android-api-compat-guide/)

ABOUT ME / 关注我:  [Github](https://github.com/liaohuqiu) | [twitter](https://twitter.com/liaohuqiu) | [微博](http://weibo.com/liaohuqiu)

#### Interface

```java
public interface ClipboardManagerCompat {

    void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener);

    void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener);

    CharSequence getText();

    void setText(CharSequence text);

    boolean hasText();
}
```

* Screen snapshot

<div><img src='https://raw.githubusercontent.com/liaohuqiu/android-ClipboardManagerCompat/master/art/clipboard-manager-compat.gif' width="300px" style='border: #f1f1f1 solid 1px'/></div>

#### Import

Repositories:

```groovy
allprojects {
    repositories {
        mavenCentral()
        maven {
            url "https://oss.sonatype.org/content/repositories/snapshots"
        }
        jcenter()
    }
}
```

Add to dependencies:

```groovy
compile 'in.srain.cube:clipboard-manager-compat:1.0.2'
```

### LICENSE

MIT
