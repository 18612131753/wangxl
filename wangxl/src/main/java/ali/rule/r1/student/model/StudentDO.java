package ali.rule.r1.student.model;

import java.util.List;

import ali.rule.r1.student.base.SexEnum;

public class StudentDO {

	private Integer id ;
	private String name ;
	private Integer sex = SexEnum.BOY.getValue() ;
	private String class_id ;
	private Boolean man ; //isman
	
	private List<TeacherDO> teachers ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public List<TeacherDO> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<TeacherDO> teachers) {
		this.teachers = teachers;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Boolean getMan() {
		return man;
	}

	public void setMan(Boolean man) {
		this.man = man;
	}

}
