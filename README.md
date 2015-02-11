Some thing about the useful tools of the **JDK 1.8**.
I find some I designer is not very good which about Java Core API.
I try to repair it with my experience and shall with you.
If there any problems then send the email to **hj13035952619@126.com**
###############################################################################

* Should has JDK1.8 or up.
* Should has Maven3 or up.

If you want to let it into the eclipse. Try to use the command:
mvn eclipse:eclipse

You could download from the maven repository, now!<br>
&lt;dependency&gt;<br>
	&lt;groupId&gt;com.github.Andy-Shao&lt;/groupId&gt;<br>
	&lt;artifactId&gt;Useful-Tools&lt;/artifactId&gt;<br>
	&lt;version&gt;${Useful-Tools.Version.Number}&lt;/version&gt;<br>
&lt;/dependency&gt;<br>
###############################################################################

**The update of v5.0**

*Add the data structure*

**The update of v4.0**

*Support JDK8 interface functional*<br>
Adjust the com.github.andyshao.convert.Convert &
com.github.andyshao.proxy.ProxyFactory support the JDK8 interface functional

*Use the interface build ProxyFactory*<br>
Now, you can use the com.github.andyshao.proxy.DynamicPF &
com.github.andyshao.proxy.CglibPF interface build the ProxyFactory.

*Improve Java System property*<br>
The com.github.andyshao.lang.GeneralSystemProperty that can get the Java System
Property more convenient is a Enum class. 
