import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieapi.R
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
class Test {

    @Test
    fun test(){
        println(ApplicationProvider.getApplicationContext<Context>().getString(R.string.app_name))
    }
}