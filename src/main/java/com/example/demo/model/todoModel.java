package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todoTable", catalog = "todoapp")
public class todoModel {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private Integer id;
	    
	    @Column(name = "taskTitle")
	    private String taskTitle;
	    
	    private Boolean complete=false;
	    
	    private Date createDate = new Date();

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTaskTitle() {
			return taskTitle;
		}

		public void setTaskTitle(String taskTitle) {
			this.taskTitle = taskTitle;
		}

		public Boolean getComplete() {
			return complete;
		}

		public void setComplete(Boolean complete) {
			this.complete = complete;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
	    
		   @Override
		    public String toString() {
		        return String.format(
		                "Todo[id=%s, title='%s', completed='%s']",
		                id, taskTitle, complete);
		    }

}
