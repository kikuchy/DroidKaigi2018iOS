//
//  SessionDetailViewController.h
//  DroidKaigi2018
//
//  Created by hiroshi.kikuchi on 2018/01/16.
//  Copyright © 2018年 DroidKaigi. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface SessionDetailViewController : UIViewController
@property (weak, nonatomic) IBOutlet UIScrollView *containerScroll;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UIImageView *speakerAvatarImage;
@property (weak, nonatomic) IBOutlet UILabel *speakerNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *placeLabel;
@property (weak, nonatomic) IBOutlet UILabel *descriptionText;

@end
