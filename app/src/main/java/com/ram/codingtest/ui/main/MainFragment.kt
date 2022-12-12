package com.ram.codingtest.ui.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ram.codingtest.databinding.FragmentMainBinding
import com.ram.codingtest.listeners.NewsItemClickListener
import com.ram.codingtest.utilis.setVisibility
import com.ram.codingtest.utilis.setVisibilityGone
import com.ram.codingtest.ui.main.adapters.NewsListAdapter
import com.ram.codingtest.utilis.Result

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
        viewModel.getNews()
        return root
    }

    private fun registerObservers() {
        viewModel.getNewsLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    newsListAdapter.setNews(result.data)
                    binding.progress.setVisibilityGone()
                }
                is Result.InProgress -> {
                    binding.progress.setVisibility()
                }
                is Result.Error -> {
                    binding.progress.setVisibilityGone()
                    Toast.makeText(activity, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onItemClick(newsLink: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(newsLink)
        startActivity(openURL)
    }

}