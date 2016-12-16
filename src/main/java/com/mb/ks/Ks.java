package com.mb.ks;

import java.util.List;
import java.util.Set;

import com.mb.preDeal.Util;

/*
 * -Shell 
分解算法分解步骤如下：
	1.假设网络中不存在度为 0 的节点，即每个节点度值 k1 ，先将网络中所有度值为 1 的节点剥落，剥落所有度值为 1 的节点后，生成的新网络中会继续有度值为 1 
的节点，重复之前的步骤，继续进行剥落度值为 1 的节点，直到新网络中所有的节点的度值 k2，把所有剥落的节点称为网络的 1-Shell（1-壳）；
	2.新网络中节点的度值有 k2，同第一步相同，重复剥落度值为 2 的节点，直到网络中所有节点的度值 k3，将这一步剥落的节点称为网络的 2-Shell。
	以此类推，进一步得到网络的 3-Shell、4-Shell 等更高的 Shell 层，直到网络中的所有的节点都被剥落，这样网络中的每个节点都有对应的 K-Shell 
值ks，例如属于 2-Shell 的节点，其 2ks ，对于属于ks-Shell 层的节点都有度值ks 
 
 */
public class Ks {
	public static void main(String[] args) {
		//sina #西安身边事#
		String repost = "data/repost.txt"; //转发
		String comment = "data/comment.txt";//评论
		String follower = "data/follower";//关注
		String user = "data/user.txt";//关注
		Set<String> nodes = Utils.getNodes(user);
		List<String> edges = Utils.getEdges(follower);
		//先统计处每个节点的度，循环去除度为1的  知道所有节点的度都大于等于2
		
	}
}
