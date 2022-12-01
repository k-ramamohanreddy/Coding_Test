package com.ram.codingtest.ui.main

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ram.codingtest.databinding.FragmentMainBinding
import com.ram.codingtest.listeners.NewsItemClickListener
import com.ram.codingtest.setVisibility
import com.ram.codingtest.setVisibilityGone
import com.ram.codingtest.ui.main.adapters.NewsListAdapter

class MainFragment : Fragment(), NewsItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        newsListAdapter = NewsListAdapter(this)
        binding.newsList.adapter = newsListAdapter
        registerObservers()
        binding.progress.setVisibility()
        viewModel.getNews()
        return root
    }

    private fun registerObservers() {
        viewModel.newsSuccessLiveData.observe(viewLifecycleOwner) { newsResponse ->
            newsResponse?.let {
                newsListAdapter.setNews(newsResponse.news)
            }
            binding.progress.setVisibilityGone()
        }
        viewModel.newsFailureLiveData.observe(viewLifecycleOwner) { isFailed ->
            isFailed?.let {
                Toast.makeText(activity?.applicationContext, "Oops! something went wrong", Toast.LENGTH_LONG).show()
            }
            binding.progress.setVisibilityGone()
        }
    }

    override fun onItemClick(newsLink: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(newsLink)
        startActivity(openURL)
    }

}