<<<<<<< HEAD:app/src/main/java/id/smkcoding/smkcodingchallenge/activity/main/profil/ProfilFragment.kt
package id.smkcoding.smkcodingchallenge.activity.main.profil
=======
package id.smkcoding.smkcodingchallenge
>>>>>>> room:app/src/main/java/id/smkcoding/smkcodingchallenge/ProfilFragment.kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.smkcoding.smkcodingchallenge.R

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
