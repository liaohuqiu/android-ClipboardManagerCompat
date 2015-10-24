Compatibility for ClipboardManager, from API level 1.

Related post: [再谈 Android API 兼容性处理](http://www.liaohuqiu.net/cn/posts/android-api-compat-guide/)

ABOUT ME / 关注我:  [Github](https://github.com/liaohuqiu) | [twitter](https://twitter.com/liaohuqiu) | [微博](http://weibo.com/liaohuqiu)

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
compile 'in.srain.cube:clipboard-manager-compat:{$lib_version}'
```

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

### LICENSE

MIT