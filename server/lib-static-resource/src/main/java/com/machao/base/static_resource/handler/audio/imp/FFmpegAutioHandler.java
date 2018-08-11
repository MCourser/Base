package com.machao.base.static_resource.handler.audio.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.machao.base.static_resource.ffmpeg.FFmpegHandler.HandlerCallback;
import com.machao.base.static_resource.ffmpeg.imp.M3u8FFmpegHandler;
import com.machao.base.static_resource.ffmpeg.imp.TsFFmpegHandler;
import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedArchException;
import com.machao.base.static_resource.ffmpeg.utils.PlatformUtils.UnsupportedPlatformException;
import com.machao.base.static_resource.handler.LockableHandler;
import com.machao.base.static_resource.handler.audio.AudioHandler;

public class FFmpegAutioHandler extends LockableHandler implements AudioHandler {
	private Logger logger = LoggerFactory.getLogger(FFmpegAutioHandler.class);
	
	private TsFFmpegHandler tsFFmpegHandler = new TsFFmpegHandler();
	private M3u8FFmpegHandler m3u8FFmpegHandler = new M3u8FFmpegHandler();

	@Override
	public void handle(File file) throws IOException, InterruptedException, UnsupportedPlatformException, UnsupportedArchException {
		List<File> tsList = new ArrayList<File>();
		this.tsFFmpegHandler.handle(file, file.getParentFile(), new HandlerCallback() {
			@Override
			public void onStart() {
				logger.info("TsFFmpegHandler ==> start: {}", file.getAbsolutePath());
				FFmpegAutioHandler.this.lock(file);
			}
			
			@Override
			public void onGenerateOutputFile(File destFile) {
				logger.info("TsFFmpegHandler ==> new file: {}", destFile.getAbsolutePath());
				tsList.add(destFile);
			}
			
			@Override
			public void onFinished() {
				logger.info("TsFFmpegHandler ==> finished: {}", file.getAbsolutePath());
				FFmpegAutioHandler.this.unlock(file);
			}
			
			@Override
			public void onError(String error) {
				logger.error("TsFFmpegHandler ==> error: {}, {}", file.getAbsolutePath(), error);
			}
		});
		
		List<File> m3u8List = new ArrayList<File>();
		for(File destFile : tsList) {
			this.m3u8FFmpegHandler.handle(destFile, destFile.getParentFile(), new HandlerCallback() {
				@Override
				public void onStart() {
					logger.info("M3u8FFmpegHandler ==> start: {}", file.getAbsolutePath());
					FFmpegAutioHandler.this.lock(file);
				}
				
				@Override
				public void onGenerateOutputFile(File destFile) {
					logger.info("M3u8FFmpegHandler ==> new file: {}", destFile.getAbsolutePath());
					m3u8List.add(destFile);
				}
				
				@Override
				public void onFinished() {
					logger.info("M3u8FFmpegHandler ==> finished: {}", file.getAbsolutePath());
					FFmpegAutioHandler.this.unlock(file);
				}
				
				@Override
				public void onError(String error) {
					logger.error("M3u8FFmpegHandler ==> error: {}, {}", file.getAbsolutePath(), error);
				}
			});
		}
	}

//	@Override
//	public void handle(File file) {
//		AVFormatContext pFormatCtx = new AVFormatContext(null);
//        int             i, videoStream;
//        AVCodecContext  pCodecCtx = null;
//        AVCodec         pCodec = null;
//        AVFrame         pFrame = null;
//        AVFrame         pFrameRGB = null;
//        AVPacket        packet = new AVPacket();
//        int[]           frameFinished = new int[1];
//        int             numBytes;
//        BytePointer     buffer = null;
//
//        AVDictionary    optionsDict = null;
//        SwsContext      sws_ctx = null;
//
//        // Register all formats and codecs
//        av_register_all();
//
//        // Open video file
//        if (avformat_open_input(pFormatCtx, args[0], null, null) != 0) {
//            System.exit(-1); // Couldn't open file
//        }
//
//        // Retrieve stream information
//        if (avformat_find_stream_info(pFormatCtx, (PointerPointer)null) < 0) {
//            System.exit(-1); // Couldn't find stream information
//        }
//
//        // Dump information about file onto standard error
//        av_dump_format(pFormatCtx, 0, args[0], 0);
//
//        // Find the first video stream
//        videoStream = -1;
//        for (i = 0; i < pFormatCtx.nb_streams(); i++) {
//            if (pFormatCtx.streams(i).codec().codec_type() == AVMEDIA_TYPE_VIDEO) {
//                videoStream = i;
//                break;
//            }
//        }
//        if (videoStream == -1) {
//            System.exit(-1); // Didn't find a video stream
//        }
//
//        // Get a pointer to the codec context for the video stream
//        pCodecCtx = pFormatCtx.streams(videoStream).codec();
//
//        // Find the decoder for the video stream
//        pCodec = avcodec_find_decoder(pCodecCtx.codec_id());
//        if (pCodec == null) {
//            System.err.println("Unsupported codec!");
//            System.exit(-1); // Codec not found
//        }
//        // Open codec
//        if (avcodec_open2(pCodecCtx, pCodec, optionsDict) < 0) {
//            System.exit(-1); // Could not open codec
//        }
//
//        // Allocate video frame
//        pFrame = av_frame_alloc();
//
//        // Allocate an AVFrame structure
//        pFrameRGB = av_frame_alloc();
//        if(pFrameRGB == null) {
//            System.exit(-1);
//        }
//
//        // Determine required buffer size and allocate buffer
//        numBytes = avpicture_get_size(AV_PIX_FMT_RGB24,
//                pCodecCtx.width(), pCodecCtx.height());
//        buffer = new BytePointer(av_malloc(numBytes));
//
//        sws_ctx = sws_getContext(pCodecCtx.width(), pCodecCtx.height(),
//                pCodecCtx.pix_fmt(), pCodecCtx.width(), pCodecCtx.height(),
//                AV_PIX_FMT_RGB24, SWS_BILINEAR, null, null, (DoublePointer)null);
//
//        // Assign appropriate parts of buffer to image planes in pFrameRGB
//        // Note that pFrameRGB is an AVFrame, but AVFrame is a superset
//        // of AVPicture
//        avpicture_fill(new AVPicture(pFrameRGB), buffer, AV_PIX_FMT_RGB24,
//                pCodecCtx.width(), pCodecCtx.height());
//
//        // Read frames and save first five frames to disk
//        i = 0;
//        while (av_read_frame(pFormatCtx, packet) >= 0) {
//            // Is this a packet from the video stream?
//            if (packet.stream_index() == videoStream) {
//                // Decode video frame
//                avcodec_decode_video2(pCodecCtx, pFrame, frameFinished, packet);
//
//                // Did we get a video frame?
//                if (frameFinished[0] != 0) {
//                    // Convert the image from its native format to RGB
//                    sws_scale(sws_ctx, pFrame.data(), pFrame.linesize(), 0,
//                            pCodecCtx.height(), pFrameRGB.data(), pFrameRGB.linesize());
//
//                    // Save the frame to disk
//                    if (++i<=5) {
//                        SaveFrame(pFrameRGB, pCodecCtx.width(), pCodecCtx.height(), i);
//                    }
//                }
//            }
//
//            // Free the packet that was allocated by av_read_frame
//            av_free_packet(packet);
//        }
//
//        // Free the RGB image
//        av_free(buffer);
//        av_free(pFrameRGB);
//
//        // Free the YUV frame
//        av_free(pFrame);
//
//        // Close the codec
//        avcodec_close(pCodecCtx);
//
//        // Close the video file
//        avformat_close_input(pFormatCtx);
//
//        System.exit(0);		
//	}

}
