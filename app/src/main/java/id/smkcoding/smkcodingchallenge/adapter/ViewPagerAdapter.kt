<<<<<<< HEAD:app/src/main/java/id/smkcoding/smkcodingchallenge/adapter/ViewPagerAdapter.kt
package id.smkcoding.smkcodingchallenge.adapter
=======
package id.smkcoding.smkcodingchallenge
>>>>>>> room:app/src/main/java/id/smkcoding/smkcodingchallenge/ViewPagerAdapter.kt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.smkcoding.smkcodingchallenge.activity.main.github.GithubFragment
import id.smkcoding.smkcodingchallenge.activity.main.myfriend.MyFriendsFragment
import id.smkcoding.smkcodingchallenge.activity.main.profil.ProfilFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
//        if (position == 0) {
//            return GithubFragment()
//        } else {
//            return GithubFragment()
//        }
        when (position) {
            0 -> { return MyFriendsFragment()
            }
            1 -> { return GithubFragment()
            }
            2 -> { return ProfilFragment()
            }
            else -> {
                return GithubFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}