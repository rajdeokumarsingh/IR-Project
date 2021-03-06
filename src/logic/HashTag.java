package logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashTag {
	
	Logger log = LoggerFactory.getLogger(HashTag.class);
	
	String name;
	
	private List<Tweet> tweets = new ArrayList<>();
	
	public HashTag(String name) {
		this.name = name;
	}

	public Double getPositivePercentage() {
		int count = 0;
		for (Tweet tweet : tweets) {
			count += tweet.getClassification() == Classification.POSITIVE ? 1 : 0;
		}
		return new Double(count) / new Double(tweets.size());
	}
	
	public Double getNegativePercentage() {
		int count = 0;
		for (Tweet tweet : tweets) {
			count += tweet.getClassification() == Classification.NEGATIVE ? 1 : 0;
		}
		return new Double(count) / new Double(tweets.size());
	}
	
	public Double getAveragePositiveWeight() {
		List<Double> weights = new ArrayList<Double>();
		for (Tweet t : tweets) {
			weights.add(t.getNormalizedPositiveWeight());
		}
		return Utils.getAverage(weights);
	}

	public Double getAverageNegativeWeight() {
		List<Double> weights = new ArrayList<Double>();
		for (Tweet t : tweets) {
			weights.add(t.getNormalizedNegativeWeight());
		}
		return Utils.getAverage(weights);
	}
	
	public Double getNormalizedPositiveWeight() {
		Double avgPosWeight = getAveragePositiveWeight();
		Double avgNegWeight = getAverageNegativeWeight();
		if((avgPosWeight + avgNegWeight) == 0.0) {
			log.warn("The hashtag '{}' have zero average positive and negative weight.",name);
			return 0.0;
		} else {
			return avgPosWeight / (avgPosWeight + avgNegWeight); 
		}
	}
	
	public Double getNormalizedNegativeWeight() {
		Double avgPosWeight = getAveragePositiveWeight();
		Double avgNegWeight = getAverageNegativeWeight();
		if((avgPosWeight + avgNegWeight) == 0.0) {
			log.warn("The hashtag '{}' have zero average positive and negative weight.",name);
			return 0.0;
		} else {
			return avgNegWeight / (avgNegWeight + avgPosWeight); 
		}
		
	}

	public void add(Tweet analyzedTweet) {
		tweets.add(analyzedTweet);
	}
	
	public void addAll(List<Tweet> analyzedTweets) {
		tweets.addAll(analyzedTweets);
	}

}
