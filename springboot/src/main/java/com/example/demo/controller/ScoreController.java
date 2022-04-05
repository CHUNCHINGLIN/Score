package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Score;
import com.example.demo.repository.ScoreRepository;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("/score")
public class ScoreController {
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	//查詢全部
	@GetMapping("/")
	public String index(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findAll()); //從資料庫抓資料
		
		return "score";
	}

	//查詢單筆
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method","PUT");
		model.addAttribute("score",scoreRepository.getById(id)); //抓到資料後直接放入表格中供修改
		model.addAttribute("scores",scoreRepository.findAll());
		return "score";
	}
	
	//刪除
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		//model.addAttribute("_method","POST");
		scoreRepository.deleteById(id);
		return "redirect:../";
	}
	
	//新增<要做驗證，所以要加上@Valid>
	@PostMapping("/")
	public String add(@Valid @ModelAttribute Score score, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("scores",scoreRepository.findAll());
			return "score";
		}
		scoreRepository.save(score); //這裡用save存到資料庫
		return "redirect:./";
	}
	
	//修改
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Score score, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method","PUT");
			model.addAttribute("scores",scoreRepository.findAll());
			return "score";
		}
		scoreRepository.save(score); //這裡用save存到資料庫
		return "redirect:./";
	}
	
	
	//查詢國文
		@GetMapping("/chinese")
		public String queryChinese(@ModelAttribute Score score,Model model) {
			model.addAttribute("_method","POST");
			model.addAttribute("scores",scoreRepository.getByChineseBetween(0, 100)); //從資料庫只抓國文成績
			return "score";
		}
		
	
	//國文排序
	@GetMapping("/chinese/DescRank")
	public String chineseDescRank(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findByOrderByChineseDesc()); //從資料庫抓資料排序
		
		return "score";
		}
	
	//英文排序
	@GetMapping("/english/DescRank")
	public String englishDescRank(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findByOrderByEnglishDesc()); //從資料庫抓資料排序
		
		return "score";
		}
	//數學排序
	@GetMapping("/math/DescRank")
	public String MathDescRank(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findByOrderByMathDesc()); //從資料庫抓資料排序
		
		return "score";
		}
	//社會排序
	@GetMapping("/society/DescRank")
	public String societyDescRank(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findByOrderBySocietyDesc()); //從資料庫抓資料排序
		
		return "score";
		}
	
	//自然排序
	@GetMapping("/science/DescRank")
	public String scienceDescRank(@ModelAttribute Score score,Model model) {
		model.addAttribute("_method","POST");
		model.addAttribute("scores",scoreRepository.findByOrderByScienceDesc()); //從資料庫抓資料排序
		
		return "score";
		}
	
	
	//加入Sample data
	@GetMapping("/add/sample/data")
	@ResponseBody
	public String addSampleDate() {
		Faker faker = new Faker();
		Random random = new Random();
		Score score=new Score();
		for(int i=0 ; i<20 ; i++) {
			
			score.setName(faker.name().lastName());
			score.setChinese(random.nextInt(100));
			score.setEnglish(random.nextInt(100));
			score.setMath(random.nextInt(100));
			score.setSociety(random.nextInt(100));
			score.setScience(random.nextInt(100));
			score.setDate(faker.date().birthday());
			score.setTotal(score.getChinese()+score.getEnglish()+score.getMath()+score.getSociety()+score.getScience());
			score.setAverage(score.getTotal()/5.0);
			scoreRepository.save(score); //要加這段save儲存至資料庫
		}
		
		for(int i=0 ; i<20 ; i++) {
			score.setTotal(score.getChinese()+score.getEnglish()+score.getMath()+score.getSociety()+score.getScience());
			score.setAverage(score.getTotal()/5.0);
			scoreRepository.save(score); //要加這段save儲存至資料庫
		}
		return "Add Sample finished.";
	}
	
	//列出 sample data
	@GetMapping("/list/sample/data")
	@ResponseBody
	public List<Score> listSampleData(){
		return scoreRepository.findAll();
	}
	
	

	
}
