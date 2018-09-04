package com.machao.base.ffmpeg.model;

import java.util.Map;

public class Stream {

	private int index;
	private String codec_name;
	private String codec_long_name;
	private String profile;
	private String codec_type;
	private String codec_time_base;
	private String codec_tag_string;
	private String codec_tag;
	private String sample_fmt;
	private String sample_rate;
	private int channels;
	private String channel_layout;
	private int bits_per_sample;
	private String r_frame_rate;
	private String avg_frame_rate;
	private String time_base;
	private int start_pts;
	private String start_time;
	private long duration_ts;
	private String duration;
	private String bit_rate;
	private String max_bit_rate;
	private String nb_frames;
	private Disposition disposition;
	private Map<String, String> tags;

	// video
	private int width;
	private int height;
	private int coded_width;
	private int coded_height;
	private int has_b_frames;
	private String sample_aspect_ratio;
	private String display_aspect_ratio;
	private String pix_fmt;
	private int level;
	private String chroma_location;
	private int refs;
	private String is_avc;
	private String nal_length_size;
	private String bits_per_raw_sample;

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setCodec_name(String codec_name) {
		this.codec_name = codec_name;
	}

	public String getCodec_name() {
		return codec_name;
	}

	public void setCodec_long_name(String codec_long_name) {
		this.codec_long_name = codec_long_name;
	}

	public String getCodec_long_name() {
		return codec_long_name;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}

	public void setCodec_type(String codec_type) {
		this.codec_type = codec_type;
	}

	public String getCodec_type() {
		return codec_type;
	}

	public void setCodec_time_base(String codec_time_base) {
		this.codec_time_base = codec_time_base;
	}

	public String getCodec_time_base() {
		return codec_time_base;
	}

	public void setCodec_tag_string(String codec_tag_string) {
		this.codec_tag_string = codec_tag_string;
	}

	public String getCodec_tag_string() {
		return codec_tag_string;
	}

	public void setCodec_tag(String codec_tag) {
		this.codec_tag = codec_tag;
	}

	public String getCodec_tag() {
		return codec_tag;
	}

	public void setSample_fmt(String sample_fmt) {
		this.sample_fmt = sample_fmt;
	}

	public String getSample_fmt() {
		return sample_fmt;
	}

	public void setSample_rate(String sample_rate) {
		this.sample_rate = sample_rate;
	}

	public String getSample_rate() {
		return sample_rate;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannel_layout(String channel_layout) {
		this.channel_layout = channel_layout;
	}

	public String getChannel_layout() {
		return channel_layout;
	}

	public void setBits_per_sample(int bits_per_sample) {
		this.bits_per_sample = bits_per_sample;
	}

	public int getBits_per_sample() {
		return bits_per_sample;
	}

	public void setR_frame_rate(String r_frame_rate) {
		this.r_frame_rate = r_frame_rate;
	}

	public String getR_frame_rate() {
		return r_frame_rate;
	}

	public void setAvg_frame_rate(String avg_frame_rate) {
		this.avg_frame_rate = avg_frame_rate;
	}

	public String getAvg_frame_rate() {
		return avg_frame_rate;
	}

	public void setTime_base(String time_base) {
		this.time_base = time_base;
	}

	public String getTime_base() {
		return time_base;
	}

	public void setStart_pts(int start_pts) {
		this.start_pts = start_pts;
	}

	public int getStart_pts() {
		return start_pts;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setDuration_ts(long duration_ts) {
		this.duration_ts = duration_ts;
	}

	public long getDuration_ts() {
		return duration_ts;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setBit_rate(String bit_rate) {
		this.bit_rate = bit_rate;
	}

	public String getBit_rate() {
		return bit_rate;
	}

	public void setMax_bit_rate(String max_bit_rate) {
		this.max_bit_rate = max_bit_rate;
	}

	public String getMax_bit_rate() {
		return max_bit_rate;
	}

	public void setNb_frames(String nb_frames) {
		this.nb_frames = nb_frames;
	}

	public String getNb_frames() {
		return nb_frames;
	}

	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCoded_width() {
		return coded_width;
	}

	public void setCoded_width(int coded_width) {
		this.coded_width = coded_width;
	}

	public int getCoded_height() {
		return coded_height;
	}

	public void setCoded_height(int coded_height) {
		this.coded_height = coded_height;
	}

	public int getHas_b_frames() {
		return has_b_frames;
	}

	public void setHas_b_frames(int has_b_frames) {
		this.has_b_frames = has_b_frames;
	}

	public String getSample_aspect_ratio() {
		return sample_aspect_ratio;
	}

	public void setSample_aspect_ratio(String sample_aspect_ratio) {
		this.sample_aspect_ratio = sample_aspect_ratio;
	}

	public String getDisplay_aspect_ratio() {
		return display_aspect_ratio;
	}

	public void setDisplay_aspect_ratio(String display_aspect_ratio) {
		this.display_aspect_ratio = display_aspect_ratio;
	}

	public String getPix_fmt() {
		return pix_fmt;
	}

	public void setPix_fmt(String pix_fmt) {
		this.pix_fmt = pix_fmt;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getChroma_location() {
		return chroma_location;
	}

	public void setChroma_location(String chroma_location) {
		this.chroma_location = chroma_location;
	}

	public int getRefs() {
		return refs;
	}

	public void setRefs(int refs) {
		this.refs = refs;
	}

	public String getIs_avc() {
		return is_avc;
	}

	public void setIs_avc(String is_avc) {
		this.is_avc = is_avc;
	}

	public String getNal_length_size() {
		return nal_length_size;
	}

	public void setNal_length_size(String nal_length_size) {
		this.nal_length_size = nal_length_size;
	}

	public String getBits_per_raw_sample() {
		return bits_per_raw_sample;
	}

	public void setBits_per_raw_sample(String bits_per_raw_sample) {
		this.bits_per_raw_sample = bits_per_raw_sample;
	}

}