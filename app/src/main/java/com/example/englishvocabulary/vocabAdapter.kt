package com.example.englishvocabulary

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vocabulary_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class vocabAdapter(
    val vocabs: ArrayList<Vocab>,
    val c: Context
) : RecyclerView.Adapter<vocabAdapter.vocabViewHolder>(), Filterable {

    var vocabularyList : ArrayList<Vocab> = vocabs

    inner class vocabViewHolder(v: View):RecyclerView.ViewHolder(v){
        var name: TextView
        var type:TextView
        var mean: TextView
        var mMenus: ImageView

        init {
            name = v.findViewById<TextView>(R.id.tvtitle)
            type = v.findViewById<TextView>(R.id.tvtype)
            mean = v.findViewById<TextView>(R.id.tvmean)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v:View) {
            val position = vocabs[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.menu_show)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(c).inflate(R.layout.activity_add_vocabulary,null)
                        val name = v.findViewById<EditText>(R.id.txttitle)
                        val type = v.findViewById<EditText>(R.id.txttype)
                        val mean = v.findViewById<EditText>(R.id.txtmean)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                                position.title = name.text.toString()
                                position.type = type.text.toString()
                                position.mean = mean.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c,"The vocabulary is Edited",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()

                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete->{
                        /**set delete*/
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                vocabs.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c,"Deleted this Information",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    //class vocabViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): vocabViewHolder {
        return vocabViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.vocabulary_item,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: vocabViewHolder, position: Int) {
        var vocabulary = vocabularyList[position]
        holder.itemView.apply {
            tvtitle.text = vocabulary.title
            tvtype.text = vocabulary.type
            tvmean.text = vocabulary.mean
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                if(charString.isEmpty()){
                    vocabularyList = vocabs
                }
                else {
                    val resultList = ArrayList<Vocab>()
                    for (row in vocabs) {
                        if(row.title.lowercase(Locale.ROOT).
                            contains(charString.lowercase(Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    vocabularyList = resultList
                }

                val filteredResults = FilterResults()
                filteredResults.values = vocabularyList
                return filteredResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                vocabularyList = p1?.values as ArrayList<Vocab>
                notifyDataSetChanged()
            }

        }

    }

    override fun getItemCount(): Int {
        return vocabularyList.size
    }


}