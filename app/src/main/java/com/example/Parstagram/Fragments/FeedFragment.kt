package com.example.Parstagram.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Parstagram.MainActivity
import com.example.Parstagram.Post
import com.example.Parstagram.PostAdapter
import com.example.Parstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

open class FeedFragment : Fragment() {

    lateinit var postRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set up the views and click listener
        postRecyclerView=  view.findViewById<RecyclerView>(R.id.postRecyclerView)

        adapter = PostAdapter(requireContext(), allPosts)
        postRecyclerView.adapter= adapter
        postRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        queryPosts()
    }




    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e!=null){
                    Log.e(MainActivity.TAG,"Error")
                }else{
                    if (posts!=null){
                        for(post in posts){
                            Log.i(MainActivity.TAG,"Post: "+post.getDescription())
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }

            }


        })
    }
    companion object{
        const val TAG ="FeedFragment"
    }
}