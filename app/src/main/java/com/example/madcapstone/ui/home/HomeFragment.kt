package com.example.madcapstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstone.R
import com.example.madcapstone.databinding.FragmentHomeBinding
import com.example.madcapstone.db.Band
import com.example.madcapstone.db.BandAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
//    private lateinit var productRepository: Repos

    private lateinit var homeViewModel: HomeViewModel
    private val bands = arrayListOf<Band>()
    private val bandAdapter = BandAdapter(bands)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
//        setSupportActionBar(binding.toolbar)
//        setContentView(binding.root)
//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        //        val payBtn: Button = root.findViewById(R.id.button)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            payBtn.setOnClickListener {  }


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvBandList)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this.bandAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        initBands()
        loadWebview(view)
//        initViews()
    }

    private fun initViews(){

    }

    private fun initBands(){
        this.bands.add( Band(
            itemText = "Guns and Roses",
            stageText = "Stage 1",
            timeText = "10:00"
        ))
        this.bands.add( Band(
            itemText = "De Dijk",
            stageText = "Stage 2",
            timeText = "10:30"
        ))
        this.bands.add( Band(
            itemText = "ColdPlay",
            stageText = "Stage 1",
            timeText = "12:30"
        ))
        this.bandAdapter.notifyDataSetChanged()
    }

    private fun loadWebview(view: View){
        val webView: WebView = view.findViewById(R.id.wbWeather)
        webView.settings.setJavaScriptEnabled(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        webView .loadUrl("https://www.buienradar.nl//")
    }
}