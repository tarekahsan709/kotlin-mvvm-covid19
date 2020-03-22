package id.rizmaulana.covid19.ui.percountry.indonesia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import id.rizmaulana.covid19.databinding.ActivityCountryIndonesiaBinding
import id.rizmaulana.covid19.ui.adapter.ItemTypeFactoryImpl
import id.rizmaulana.covid19.ui.adapter.VisitableRecyclerAdapter
import id.rizmaulana.covid19.ui.adapter.viewholders.DailyItem
import id.rizmaulana.covid19.ui.adapter.viewholders.TextItem
import id.rizmaulana.covid19.ui.base.BaseActivity
import id.rizmaulana.covid19.ui.base.BaseViewItem
import id.rizmaulana.covid19.ui.dailygraph.DailyGraphActivity
import id.rizmaulana.covid19.util.ext.observe
import kotlinx.android.synthetic.main.activity_country_indonesia.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class CountryIndonesiaActivity : BaseActivity() {
    private val viewModel by viewModel<CountryIndonesiaViewModel>()
    private lateinit var binding: ActivityCountryIndonesiaBinding
    private val viewAdapter by lazy {
        VisitableRecyclerAdapter(
            ItemTypeFactoryImpl(),
            ::onItemClicked
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryIndonesiaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithBackButton(binding.root.toolbar)
        initView()

        viewModel.loadData()

    }

    private fun initView() {
        with(recycler_view) {
            adapter = viewAdapter
            setHasFixedSize(true)
        }
    }

    override fun observeChange() {
        observe(viewModel.items, ::onDataLoaded)
        observe(viewModel.toastMessage, ::showSnackbarMessage)

    }

    private fun onDataLoaded(items: List<BaseViewItem>) {
        viewAdapter.submitList(items)
    }


    private fun onItemClicked(viewItem: BaseViewItem, view: View) {
        when (viewItem) {
            is DailyItem -> {

            }
            is TextItem -> {
                DailyGraphActivity.startActivity(this)
            }
        }
    }


    companion object {
        @JvmStatic
        fun startActivity(context: Context?) =
            context?.startActivity(Intent(context, CountryIndonesiaActivity::class.java))
    }
}
