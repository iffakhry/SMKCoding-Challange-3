<<<<<<< HEAD:app/src/main/java/id/smkcoding/smkcodingchallenge/activity/main/MainActivity.kt
package id.smkcoding.smkcodingchallenge.activity.main
=======
package id.smkcoding.smkcodingchallenge
>>>>>>> room:app/src/main/java/id/smkcoding/smkcodingchallenge/MainActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val menuTeks = arrayOf("Teman", "Github", "profil")
    val manuIcon = arrayOf(
        R.drawable.ic_home,
        R.drawable.ic_add,
        R.drawable.ic_profile
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter =
            ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(resources, manuIcon[position], null)
            }).attach()
    }
}
